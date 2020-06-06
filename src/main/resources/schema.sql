DROP TABLE IF EXISTS vehicles;

CREATE TABLE vehicles (
  vin VARCHAR(100) PRIMARY KEY,
  make VARCHAR(100) NOT NULL,
  model VARCHAR(250) NOT NULL,
  year INTEGER NOT NULL,
  redlineRpm INTEGER NOT NULL,
  maxFuelVolume INTEGER NOT NULL,
  lastServiceDate DATE NOT NULL
);

DROP TABLE IF EXISTS readings;

CREATE TABLE readings (
  rid VARCHAR (100) PRIMARY KEY,
  vin VARCHAR(100),
  latitude FLOAT,
  longitude FLOAT,
  timestamp FLOAT,
  fuelVolume FLOAT,
  speed FLOAT,
  engineLightOn Boolean,
  engineCoolantLow Boolean,
  cruiseControlOn Boolean,
  engineRpm FLOAT
);

--DROP TABLE IF EXISTS alerts;
--
--CREATE TABLE alerts (
 --idid VARCHAR(100) PRIMARY KEY
--   make VARCHAR(100) NOT NULL,
--   model VARCHAR(250) NOT NULL,
--   year INTEGER NOT NULL,
--   redlineRpm INTEGER NOT NULL,
--   maxFuelVolume INTEGER NOT NULL,
--   lastServiceDate DATE NOT NULL
--);
