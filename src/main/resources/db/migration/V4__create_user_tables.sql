CREATE TABLE users.profiles (
    id UUID PRIMARY KEY,
    auth_user_id UUID NOT NULL UNIQUE,
    first_name VARCHAR(100),
    middle_name VARCHAR(100),
    last_name VARCHAR(100),
    headline VARCHAR(255),
    bio TEXT,
    avatar_url VARCHAR(500),
    website VARCHAR(255),
    github_url VARCHAR(255),
    linkedin_url VARCHAR(255),
    extra_info JSONB NOT NULL DEFAULT '{}'::jsonb,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);