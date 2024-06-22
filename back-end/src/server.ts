import express from 'express'
import routes from './routes';

const app = express();



app.use(express.json())


app.use(routes)


app.listen(8888, () => 'server running on port 8888')