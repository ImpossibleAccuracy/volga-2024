CREATE TABLE "Account" (
  "id" SERIAL PRIMARY KEY,
  "username" varchar NOT NULL,
  "password" varchar NOT NULL,
  "first_name" varchar NOT NULL,
  "last_name" varchar NOT NULL,
  "deleted" boolean NOT NULL
);

CREATE TABLE "Role" (
  "id" SERIAL PRIMARY KEY,
  "title" varchar UNIQUE NOT NULL
);

CREATE TABLE "RevokedToken" (
  "id" SERIAL PRIMARY KEY,
  "token" varchar UNIQUE NOT NULL
);

CREATE TABLE "Role_Account" (
  "role_id" int,
  "account_id" int,
  PRIMARY KEY ("role_id", "account_id")
);

CREATE TABLE "Hospital" (
  "id" SERIAL PRIMARY KEY,
  "created_at" timestamp NOT NULL,
  "creator_id" int NOT NULL,
  "name" varchar NOT NULL,
  "address" varchar NOT NULL,
  "contact_phone" varchar NOT NULL,
  "deleted" boolean NOT NULL
);

CREATE TABLE "Room" (
  "id" SERIAL PRIMARY KEY,
  "name" varchar NOT NULL,
  "hospital_id" int NOT NULL
);

CREATE TABLE "Timetable" (
  "id" SERIAL PRIMARY KEY,
  "created_at" timestamp NOT NULL,
  "creator_id" int NOT NULL,
  "hospital_id" int NOT NULL,
  "doctor_id" int NOT NULL,
  "date_from" timestamp NOT NULL,
  "date_to" timestamp NOT NULL,
  "room_id" int NOT NULL,
  "deleted" boolean NOT NULL
);

CREATE TABLE "Appointments" (
  "id" SERIAL PRIMARY KEY,
  "created_at" timestamp NOT NULL,
  "creator_id" int NOT NULL,
  "timetable_id" int NOT NULL,
  "time" timestamp NOT NULL,
  "deleted" boolean NOT NULL
);

CREATE TABLE "History" (
  "id" SERIAL PRIMARY KEY,
  "created_at" timestamp NOT NULL,
  "creator_id" int NOT NULL,
  "date" timestamp NOT NULL,
  "patient_id" int NOT NULL,
  "hospital_id" int NOT NULL,
  "doctor_id" int NOT NULL,
  "room" varchar NOT NULL,
  "data" text NOT NULL
);

ALTER TABLE "Role_Account" ADD FOREIGN KEY ("role_id") REFERENCES "Role" ("id");

ALTER TABLE "Role_Account" ADD FOREIGN KEY ("account_id") REFERENCES "Account" ("id");

ALTER TABLE "Hospital" ADD FOREIGN KEY ("creator_id") REFERENCES "Account" ("id");

ALTER TABLE "Room" ADD FOREIGN KEY ("hospital_id") REFERENCES "Hospital" ("id");

ALTER TABLE "Timetable" ADD FOREIGN KEY ("creator_id") REFERENCES "Account" ("id");

ALTER TABLE "Timetable" ADD FOREIGN KEY ("hospital_id") REFERENCES "Hospital" ("id");

ALTER TABLE "Timetable" ADD FOREIGN KEY ("doctor_id") REFERENCES "Account" ("id");

ALTER TABLE "Timetable" ADD FOREIGN KEY ("room_id") REFERENCES "Room" ("id");

ALTER TABLE "Appointments" ADD FOREIGN KEY ("creator_id") REFERENCES "Account" ("id");

ALTER TABLE "Appointments" ADD FOREIGN KEY ("timetable_id") REFERENCES "Timetable" ("id");

ALTER TABLE "History" ADD FOREIGN KEY ("creator_id") REFERENCES "Account" ("id");

ALTER TABLE "History" ADD FOREIGN KEY ("patient_id") REFERENCES "Account" ("id");

ALTER TABLE "History" ADD FOREIGN KEY ("hospital_id") REFERENCES "Hospital" ("id");

ALTER TABLE "History" ADD FOREIGN KEY ("doctor_id") REFERENCES "Account" ("id");
