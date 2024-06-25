import express from 'express';
import bodyParser from 'body-parser';
import router from './routes/router';
import sequelize from '../src/database/database';

const app = express();
const PORT = 3000;

app.use(bodyParser.json());
app.use('/api', router);

sequelize.authenticate().then(() => {
  console.log('Database connected successfully.');
  app.listen(PORT, () => {
    console.log(`Server is running on http://localhost:${PORT}`);
  });
}).catch((error) => {
  console.error('Unable to connect to the database:', error);
});
