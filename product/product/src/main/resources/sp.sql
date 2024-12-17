CREATE OR REPLACE FUNCTION find_product_by_code(code_input VARCHAR)
RETURNS TABLE(id BIGINT, code VARCHAR, name VARCHAR)
LANGUAGE plpgsql AS $$
BEGIN
    RETURN QUERY(
        SELECT p.id, p.code, p.name
        FROM product_entity p
        WHERE p.code = code_input
    );
END;
$$ ;

CREATE OR REPLACE FUNCTION update_customer_name(
    in_id BIGINT,
    in_code VARCHAR,
    in_name VARCHAR,
    in_phone VARCHAR,
    in_iban VARCHAR,
    in_surname VARCHAR,
    in_address VARCHAR
)
RETURNS VOID AS $$
BEGIN
    UPDATE customer
    SET
        code = in_code,
        name = in_name,
        phone = in_phone,
        iban = in_iban,
        surname = in_surname,
        address = in_address
    WHERE id = in_id;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_transaction_by_account_iban(iban TEXT)
RETURNS SETOF transactions
LANGUAGE plpgsql AS
$$
BEGIN
    RETURN QUERY(
        SELECT * FROM transactions WHERE account_iban = iban
    );
END;
$$;

--select * from get_transaction_by_account_iban('22332')


CREATE OR REPLACE FUNCTION insert_transaction(
    in_reference VARCHAR,
    in_account_iban VARCHAR,
    in_ammount DOUBLE PRECISION,
    in_description VARCHAR,
    in_status VARCHAR,
    in_channel VARCHAR
)
RETURNS VOID AS $$
DECLARE
    next_id BIGINT;
BEGIN
    SELECT COALESCE(MAX(id), 0) + 1 INTO next_id FROM transactions;
    INSERT INTO transactions (id, reference, account_iban, date, ammount, description, status, channel)
    VALUES (next_id, in_reference, in_account_iban, NOW(), in_ammount, in_description, in_status, in_channel);
END;
$$ LANGUAGE plpgsql;
