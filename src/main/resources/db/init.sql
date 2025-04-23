-- Drop tables if they exist (in correct order due to foreign key constraints)
DROP TABLE IF EXISTS productinstallmentdetailtbl CASCADE;
DROP TABLE IF EXISTS purchasedproducttbl CASCADE;
DROP TABLE IF EXISTS generalproducttbl CASCADE;
DROP TABLE IF EXISTS customertbl CASCADE;
DROP TABLE IF EXISTS sellertbl CASCADE;

-- Create sequence for auto-incrementing IDs
CREATE SEQUENCE IF NOT EXISTS product_code_seq START 1;
CREATE SEQUENCE IF NOT EXISTS purchased_product_code_seq START 1;
CREATE SEQUENCE IF NOT EXISTS installment_code_seq START 1;

-- Create customer table
CREATE TABLE IF NOT EXISTS customertbl (
    nationalid VARCHAR(20) PRIMARY KEY,
    fathersname VARCHAR(100) NOT NULL,
    firstname VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    emailverified BOOLEAN DEFAULT FALSE,
    address TEXT NOT NULL,
    phonenumber VARCHAR(20) NOT NULL UNIQUE,
    phoneverified BOOLEAN DEFAULT FALSE,
    encryptedpassword VARCHAR(255) NOT NULL,
    dateofbirth DATE NOT NULL,
    firstrelativephonenumber VARCHAR(20) NOT NULL,
    secondrelativephonenumber VARCHAR(20) NOT NULL,
    thirdrelativephonenumber VARCHAR(20) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create seller table
CREATE TABLE IF NOT EXISTS sellertbl (
    nationalid VARCHAR(20) PRIMARY KEY,
    firstname VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    username VARCHAR(100) NOT NULL UNIQUE,
    phonenumber VARCHAR(20) NOT NULL UNIQUE,
    phoneverified BOOLEAN DEFAULT FALSE,
    email VARCHAR(255) NOT NULL UNIQUE,
    emailverified BOOLEAN DEFAULT FALSE,
    storeaddress TEXT NOT NULL,
    encryptedpassword VARCHAR(255) NOT NULL,
    storeplate INTEGER NOT NULL,
    storename VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create general product table
CREATE TABLE IF NOT EXISTS generalproducttbl (
    productcode INTEGER PRIMARY KEY DEFAULT nextval('product_code_seq'),
    productname VARCHAR(255) NOT NULL,
    productcategory VARCHAR(100) NOT NULL,
    productmodel VARCHAR(100) NOT NULL,
    productserialnumber VARCHAR(100) NOT NULL UNIQUE,
    productcolor VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create purchased product table
CREATE TABLE IF NOT EXISTS purchasedproducttbl (
    purchasedproductcode INTEGER PRIMARY KEY DEFAULT nextval('purchased_product_code_seq'),
    productprice DECIMAL(15,2) NOT NULL CHECK (productprice > 0),
    productpurchaseininstallment BOOLEAN NOT NULL,
    productnumofinstallments INTEGER NOT NULL CHECK (productnumofinstallments > 0),
    purchasedproductid INTEGER NOT NULL,
    customernationalid VARCHAR(20) NOT NULL,
    sellernationalid VARCHAR(20) NOT NULL,
    purchaseddate DATE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (purchasedproductid) REFERENCES generalproducttbl(productcode),
    FOREIGN KEY (customernationalid) REFERENCES customertbl(nationalid),
    FOREIGN KEY (sellernationalid) REFERENCES sellertbl(nationalid)
);

-- Create product installment detail table
CREATE TABLE IF NOT EXISTS productinstallmentdetailtbl (
    installmentcode INTEGER PRIMARY KEY DEFAULT nextval('installment_code_seq'),
    customernationalid VARCHAR(20) NOT NULL,
    installmentpaymentdate DATE NOT NULL,
    productid INTEGER NOT NULL,
    sellernationalid VARCHAR(20) NOT NULL,
    installmentindex INTEGER NOT NULL CHECK (installmentindex > 0),
    installmentpricetopay DECIMAL(15,2) NOT NULL CHECK (installmentpricetopay > 0),
    ispaid BOOLEAN DEFAULT FALSE,
    paymentdate TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customernationalid) REFERENCES customertbl(nationalid),
    FOREIGN KEY (productid) REFERENCES generalproducttbl(productcode),
    FOREIGN KEY (sellernationalid) REFERENCES sellertbl(nationalid)
);

-- Create indexes for better performance
CREATE INDEX IF NOT EXISTS idx_customer_email ON customertbl(email);
CREATE INDEX IF NOT EXISTS idx_customer_phone ON customertbl(phonenumber);
CREATE INDEX IF NOT EXISTS idx_seller_email ON sellertbl(email);
CREATE INDEX IF NOT EXISTS idx_seller_phone ON sellertbl(phonenumber);
CREATE INDEX IF NOT EXISTS idx_seller_username ON sellertbl(username);
CREATE INDEX IF NOT EXISTS idx_product_category ON generalproducttbl(productcategory);
CREATE INDEX IF NOT EXISTS idx_product_serial ON generalproducttbl(productserialnumber);
CREATE INDEX IF NOT EXISTS idx_purchased_date ON purchasedproducttbl(purchaseddate);
CREATE INDEX IF NOT EXISTS idx_installment_payment_date ON productinstallmentdetailtbl(installmentpaymentdate);
CREATE INDEX IF NOT EXISTS idx_installment_paid_status ON productinstallmentdetailtbl(ispaid);

-- Create trigger to update timestamp
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ language 'plpgsql';

-- Create triggers for all tables
CREATE TRIGGER update_customer_updated_at
    BEFORE UPDATE ON customertbl
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_seller_updated_at
    BEFORE UPDATE ON sellertbl
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_product_updated_at
    BEFORE UPDATE ON generalproducttbl
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_purchased_product_updated_at
    BEFORE UPDATE ON purchasedproducttbl
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_installment_updated_at
    BEFORE UPDATE ON productinstallmentdetailtbl
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column(); 