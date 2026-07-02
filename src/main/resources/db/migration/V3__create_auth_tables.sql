CREATE TABLE auth.users (
    id UUID PRIMARY KEY,
    role VARCHAR(50) NOT NULL DEFAULT 'USER',
    username VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    contact_number VARCHAR(20) NOT NULL UNIQUE ,
    status VARCHAR(50) NOT NULL DEFAULT 'ACTIVE',
    email_verified BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE auth.refresh_tokens (
     id UUID PRIMARY KEY,
     user_id UUID NOT NULL,
     token VARCHAR(512) NOT NULL UNIQUE,
     expires_at TIMESTAMPTZ NOT NULL,
     revoked BOOLEAN NOT NULL DEFAULT FALSE,
     created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
     updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

     CONSTRAINT fk_refresh_token_user
         FOREIGN KEY (user_id)
             REFERENCES auth.users(id)
             ON DELETE CASCADE
);

CREATE INDEX idx_refresh_tokens_user
    ON auth.refresh_tokens(user_id);