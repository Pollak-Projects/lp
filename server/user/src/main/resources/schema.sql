create schema if not exists user_data;

-- TODO remove these, its just for testing
drop table if exists user_data.user_data;

create table if not exists user_data.user_data (
    id UUID primary key,
    user_id UUID,
    profile_picture UUID,
    created_at TIMESTAMP
);