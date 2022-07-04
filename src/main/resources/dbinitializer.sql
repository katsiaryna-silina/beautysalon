-- giving grants to 'admin' in order to create function for sequence
GRANT SYSTEM_VARIABLES_ADMIN ON *.* TO 'admin'@'%';
GRANT SUPER ON *.* TO 'admin'@'%';
FLUSH PRIVILEGES;
