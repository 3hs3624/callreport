CREATE TABLE IF NOT EXISTS call_report (
    id SERIAL PRIMARY KEY,
    support_month VARCHAR(10),
    team VARCHAR(50),
    manager VARCHAR(50),
    customer_name VARCHAR(100),
    customer_manager VARCHAR(100),
    support_date DATE,
    status VARCHAR(20)
);


