-- Create tables for the NesveSib application

-- Create customer table
CREATE TABLE IF NOT EXISTS customertbl (
    nationalid VARCHAR(20) PRIMARY KEY,
    fathersname VARCHAR(100) NOT NULL,
    firstname VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL,
    emailverified BOOLEAN DEFAULT FALSE,
    address TEXT NOT NULL,
    phonenumber VARCHAR(20) NOT NULL,
    phoneverified BOOLEAN DEFAULT FALSE,
    encryptedpassword VARCHAR(255) NOT NULL,
    dateofbirth DATE NOT NULL,
    firstrelativephonenumber VARCHAR(20) NOT NULL,
    secondrelativephonenumber VARCHAR(20) NOT NULL,
    thirdrelativephonenumber VARCHAR(20) NOT NULL
);

-- Create seller table
CREATE TABLE IF NOT EXISTS sellertbl (
    nationalid VARCHAR(20) PRIMARY KEY,
    firstname VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    username VARCHAR(100) NOT NULL,
    phonenumber VARCHAR(20) NOT NULL,
    phoneverified BOOLEAN DEFAULT FALSE,
    email VARCHAR(255) NOT NULL,
    emailverified BOOLEAN DEFAULT FALSE,
    storeaddress TEXT NOT NULL,
    encryptedpassword VARCHAR(255) NOT NULL,
    storeplate INTEGER NOT NULL,
    storename VARCHAR(255) NOT NULL
);

-- Create general product table
CREATE TABLE IF NOT EXISTS generalproducttbl (
    productcode INTEGER PRIMARY KEY,
    productname VARCHAR(255) NOT NULL,
    productcategory VARCHAR(100) NOT NULL,
    productmodel VARCHAR(100) NOT NULL,
    productserialnumber VARCHAR(100) NOT NULL,
    productcolor VARCHAR(50) NOT NULL
);

-- Create purchased product table
CREATE TABLE IF NOT EXISTS purchasedproducttbl (
    purchasedproductcode INTEGER PRIMARY KEY,
    productprice DECIMAL(10,2) NOT NULL,
    productpurchaseininstallment BOOLEAN NOT NULL,
    productnumofinstallments INTEGER NOT NULL,
    purchasedproductid INTEGER NOT NULL,
    customernationalid VARCHAR(20) NOT NULL,
    sellernationalid VARCHAR(20) NOT NULL,
    purchaseddate DATE NOT NULL,
    FOREIGN KEY (purchasedproductid) REFERENCES generalproducttbl(productcode),
    FOREIGN KEY (customernationalid) REFERENCES customertbl(nationalid),
    FOREIGN KEY (sellernationalid) REFERENCES sellertbl(nationalid)
);

-- Create product installment detail table
CREATE TABLE IF NOT EXISTS productinstallmentdetailtbl (
    installmentcode INTEGER PRIMARY KEY,
    customernationalid VARCHAR(20) NOT NULL,
    installmentpaymentdate DATE NOT NULL,
    productid INTEGER NOT NULL,
    sellernationalid VARCHAR(20) NOT NULL,
    installmentindex INTEGER NOT NULL,
    installmentpricetopay DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (customernationalid) REFERENCES customertbl(nationalid),
    FOREIGN KEY (productid) REFERENCES generalproducttbl(productcode),
    FOREIGN KEY (sellernationalid) REFERENCES sellertbl(nationalid)
);

-- Create indexes for better performance
CREATE INDEX IF NOT EXISTS idx_customer_email ON customertbl(email);
CREATE INDEX IF NOT EXISTS idx_customer_phone ON customertbl(phonenumber);
CREATE INDEX IF NOT EXISTS idx_seller_email ON sellertbl(email);
CREATE INDEX IF NOT EXISTS idx_seller_phone ON sellertbl(phonenumber);
CREATE INDEX IF NOT EXISTS idx_product_category ON generalproducttbl(productcategory);
CREATE INDEX IF NOT EXISTS idx_purchased_product_customer ON purchasedproducttbl(customernationalid);
CREATE INDEX IF NOT EXISTS idx_purchased_product_seller ON purchasedproducttbl(sellernationalid);
CREATE INDEX IF NOT EXISTS idx_installment_customer ON productinstallmentdetailtbl(customernationalid);
CREATE INDEX IF NOT EXISTS idx_installment_seller ON productinstallmentdetailtbl(sellernationalid); 