CREATE TABLE sector (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    parent_id INTEGER,
    FOREIGN KEY (parent_id) REFERENCES sector(id)
);

CREATE INDEX idx_sector_parent_id ON sector (parent_id);
