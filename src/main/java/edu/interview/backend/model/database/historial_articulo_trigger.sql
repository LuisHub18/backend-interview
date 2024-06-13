CREATE OR REPLACE FUNCTION respaldo_articulo_trigger()
RETURNS TRIGGER AS $$
BEGIN
    IF TG_OP = 'INSERT' THEN
        INSERT INTO historial_articulo (articulo_id, cantidad, descripcion, fecha_creacion, fecha_operacion, modelo_id, nombre, operacion, precio)
        VALUES (NEW.id, NEW.cantidad, NEW.descripcion, NEW.fecha_creacion, CURRENT_TIMESTAMP, NEW.modelo_id, NEW.nombre, 'INSERT', NEW.precio);
    RETURN NEW;
    ELSIF TG_OP = 'UPDATE' THEN
        INSERT INTO historial_articulo (articulo_id, cantidad, descripcion, fecha_creacion, fecha_operacion, modelo_id, nombre, operacion, precio)
        VALUES (NEW.id, NEW.cantidad, NEW.descripcion, NEW.fecha_creacion, CURRENT_TIMESTAMP, NEW.modelo_id, NEW.nombre, 'UPDATE', NEW.precio);
    RETURN NEW;
    ELSIF TG_OP = 'DELETE' THEN
        INSERT INTO historial_articulo (articulo_id, cantidad, descripcion, fecha_creacion, fecha_operacion, modelo_id, nombre, operacion, precio)
        VALUES (OLD.id, OLD.cantidad, OLD.descripcion, OLD.fecha_creacion, CURRENT_TIMESTAMP, OLD.modelo_id, OLD.nombre, 'DELETE', OLD.precio);
    RETURN OLD;
    END IF;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_respaldo_articulo
    AFTER INSERT OR UPDATE OR DELETE ON articulo
    FOR EACH ROW EXECUTE FUNCTION respaldo_articulo_trigger();