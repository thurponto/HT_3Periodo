import mysql from 'mysql2/promise';

const connection = mysql.createPool({
  host: 'localhost',
  user: 'root',
  password: '',
  database: 'vacinacao_domiciliar',
});

export default connection;