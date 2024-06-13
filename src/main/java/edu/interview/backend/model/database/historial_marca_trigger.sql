CREATE OR REPLACE FUNCTION respaldo_marca_trigger()
RETURNS TRIGGER AS $$
BEGIN
    IF TG_OP = 'INSERT' THEN
            INSERT INTO historial_marca (fecha_operacion, marca_id, nombre, operacion)
            VALUES (CURRENT_TIMESTAMP, NEW.id, NEW.nombre, 'INSERT');
    RETURN NEW;
    ELSIF TG_OP = 'UPDATE' THEN
            INSERT INTO historial_marca (fecha_operacion, marca_id, nombre, operacion)
            VALUES (CURRENT_TIMESTAMP, NEW.id, NEW.nombre, 'UPDATE');
    RETURN NEW;
    ELSIF TG_OP = 'DELETE' THEN
            INSERT INTO historial_marca (fecha_operacion, marca_id, nombre, operacion)
            VALUES (CURRENT_TIMESTAMP, OLD.id, OLD.nombre, 'DELETE');
    RETURN OLD;
    END IF;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_respaldo_marca
    AFTER INSERT OR UPDATE OR DELETE ON marca
    FOR EACH ROW EXECUTE FUNCTION respaldo_marca_trigger();