CREATE TABLE user_sector (
    user_id INTEGER NOT NULL,
    sector_id INTEGER NOT NULL,
    PRIMARY KEY (user_id, sector_id),
    FOREIGN KEY (user_id) REFERENCES "app_user"(id),
    FOREIGN KEY (sector_id) REFERENCES sector(id)
);

CREATE INDEX idx_user_sector_user ON user_sector (user_id);
CREATE INDEX idx_user_sector_sector ON user_sector (sector_id);
