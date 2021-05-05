CREATE TABLE "users" (
  "id" bigserial PRIMARY KEY,
  "email" varchar NOT NULL,
  "password" varchar NOT NULL,
  "role" varchar NOT NULL,
  "status" int NOT NULL,
  "created_at" timestamptz NOT NULL DEFAULT (now())
);

CREATE TABLE "equipment" (
  "id" bigserial PRIMARY KEY,
  "name" varchar NOT NULL,
  "total_amount" int NOT NULL,
  "description" varchar,
  "created_at" timestamptz NOT NULL DEFAULT (now()),
  "updated_at" timestamptz NOT NULL DEFAULT (now())
);

CREATE TABLE "room" (
  "id" bigserial PRIMARY KEY,
  "name" varchar NOT NULL,
  "code" varchar NOT NULL,
  "room_type" int NOT NULL,
  "open_time" numeric(4,2) NOT NULL,
  "close_time" numeric(4,2) NOT NULL,
  "open_day_of_week" int NOT NULL,
  "close_day_of_week" int NOT NULL,
  "created_at" timestamptz NOT NULL DEFAULT (now()),
  "updated_at" timestamptz NOT NULL DEFAULT (now())
);

CREATE TABLE "equipment_of_room" (
  "id" bigserial PRIMARY KEY,
  "room_id" bigint NOT NULL,
  "equipment_id" bigint NOT NULL,
  "amount" int NOT NULL,
  "created_at" timestamptz NOT NULL DEFAULT (now()),
  "updated_at" timestamptz NOT NULL DEFAULT (now())
);

CREATE TABLE "room_registration" (
  "id" varchar PRIMARY KEY,
  "user_id" bigint NOT NULL,
  "group_size" int NOT NULL,
  "date" timestamptz NOT NULL,
  "start_time" numeric(4,2) NOT NULL,
  "end_time" numeric(4,2) NOT NULL,
  "room_type" int NOT NULL,
  "status" int NOT NULL,
  "created_at" timestamptz NOT NULL DEFAULT (now()),
  "updated_at" timestamptz NOT NULL DEFAULT (now())
);

CREATE TABLE "equipment_registration" (
  "room_registration_id" varchar NOT NULL,
  "equipment_id" bigint NOT NULL,
  "amount" int NOT NULL,
  "created_at" timestamptz NOT NULL DEFAULT (now()),
  "updated_at" timestamptz NOT NULL DEFAULT (now()),
  PRIMARY KEY("room_registration_id", "equipment_id")
);

CREATE TABLE "room_division_results" (
  "room_registration_id" varchar PRIMARY KEY,
  "status" int NOT NULL,
  "room_id" bigint NOT NULL,
  "date" timestamptz NOT NULL,
  "start_time" numeric(4,2) NOT NULL,
  "end_time" numeric(4,2) NOT NULL,
  "created_at" timestamptz NOT NULL DEFAULT (now()),
  "updated_at" timestamptz NOT NULL DEFAULT (now())
);

CREATE TABLE "equipment_division_result" (
  "room_registration_id" varchar,
  "status" int NOT NULL,
  "equipment_id" bigint NOT NULL,
  "amount" int NOT NULL,
  "created_at" timestamptz NOT NULL DEFAULT (now()),
  "updated_at" timestamptz NOT NULL DEFAULT (now()),
  PRIMARY KEY("room_registration_id", "equipment_id")
);

ALTER TABLE "equipment_of_room" ADD FOREIGN KEY ("room_id") REFERENCES "room" ("id");

ALTER TABLE "equipment_of_room" ADD FOREIGN KEY ("equipment_id") REFERENCES "equipment" ("id");

ALTER TABLE "room_registration" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "equipment_registration" ADD FOREIGN KEY ("room_registration_id") REFERENCES "room_registration" ("id");

ALTER TABLE "equipment_registration" ADD FOREIGN KEY ("equipment_id") REFERENCES "equipment" ("id");

ALTER TABLE "room_division_results" ADD FOREIGN KEY ("room_id") REFERENCES "room" ("id");

ALTER TABLE "equipment_division_result" ADD FOREIGN KEY ("room_registration_id") REFERENCES "room_division_results" ("room_registration_id");

ALTER TABLE "equipment_division_result" ADD FOREIGN KEY ("equipment_id") REFERENCES "equipment" ("id");
