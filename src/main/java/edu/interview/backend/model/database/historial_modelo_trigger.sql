CREATE OR REPLACE FUNCTION respaldo_modelo_trigger()
RETURNS TRIGGER AS $$
BEGIN
    IF TG_OP = 'INSERT' THEN
            INSERT INTO historial_modelo (fecha_operacion, marca_id, modelo_id, nombre, operacion)
            VALUES (CURRENT_TIMESTAMP, NEW.marca_id, NEW.id, NEW.nombre, 'INSERT');
    RETURN NEW;
    ELSIF TG_OP = 'UPDATE' THEN
            INSERT INTO historial_modelo (fecha_operacion, marca_id, modelo_id, nombre, operacion)
            VALUES (CURRENT_TIMESTAMP, NEW.marca_id, NEW.id, NEW.nombre, 'UPDATE');
    RETURN NEW;
    ELSIF TG_OP = 'DELETE' THEN
            INSERT INTO historial_modelo (fecha_operacion, marca_id, modelo_id, nombre, operacion)
            VALUES (CURRENT_TIMESTAMP, OLD.marca_id, OLD.id, OLD.nombre, 'DELETE');
    RETURN OLD;
    END IF;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER trigger_respaldo_modelo
    AFTER INSERT OR UPDATE OR DELETE ON modelo
    FOR EACH ROW EXECUTE FUNCTION respaldo_modelo_trigger();