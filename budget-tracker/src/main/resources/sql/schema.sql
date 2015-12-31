CREATE TABLE currencies (
  iso_code CHAR(3),
  format VARCHAR(20),
  PRIMARY KEY (iso_code)
);

CREATE TABLE eur_rates (
  currency_code CHAR(3),
  yyyy_mm_dd CHAR(10),
  rate DECIMAL,
  PRIMARY KEY (currency_code, yyyy_mm_dd)
);

CREATE TABLE accounts (
  id IDENTITY,
  name VARCHAR(255),
  currency CHAR(3),
  active_from DATE,
  active_to DATE,
  foreign key (default_currency) references currencies(iso_code)
);

CREATE TABLE tracking_points (
  tracking_date date,
  description VARCHAR(255),
  notes VARCHAR(10000),
  primary key (tracking_date)
);

CREATE TABLE balances (
  tracking_date date,
  account_id integer,
  amount DECIMAL(10,2),
  currency CHAR(3),
  FOREIGN KEY (account_id) references accounts(id),
  PRIMARY KEY (tracking_date, account_id)
);

INSERT INTO currencies VALUES ('EUR', U&'\u20ac#'),('USD', '$#'),('CAD', 'C$#'),('PLN','# zł');
INSERT INTO accounts (name, currency, active_from, active_to) VALUES
  ('mBank',   'PLN', '2013-12-01', '2030-01-01'),
  ('mBank',   'EUR', '2013-12-01', '2030-01-01'),
  ('mBank',   'USD', '2013-12-01', '2030-01-01'),
  ('BZ WBK',  'PLN', '2013-12-01', '2014-08-31'),
  ('Pekao',   'PLN', '2014-04-01', '2030-01-01'),
  ('gotówka', 'PLN', '2013-12-01', '2030-01-01'),
  ('gotówka',           'EUR', '2013-12-01', '2030-01-01'),
  ('GPW',               'PLN', '2013-12-01', '2030-01-01'),
  ('Lokaty terminowe',  'PLN', '2013-12-01', '2014-08-01'),
  ('Rachunek oszcz. getin', 'PLN', '2013-12-01', '2015-04-01'),
  ('ROR ABN AMRO', 'EUR', '2014-09-01', '2030-01-01'),
  ('ABN AMRO DKS', 'EUR', '2014-09-01', '2030-01-01'),
  ('mBank KK'), 'PLN', '2013-12-01', '2030-01-01'),
  ('ABN AMRO KK'), 'EUR', '2014-09-01', '2030-01-01');