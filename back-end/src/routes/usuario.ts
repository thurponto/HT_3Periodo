import { Router, Request, Response } from 'express';
import db from '../database/db';
const router = Router();

router.post("/", async (req: Request, res: Response) => {
    const usuario = req.body;

    try {
        const [rows] = await db.execute('SELECT email, senha FROM usuario WHERE email = ? AND senha=?', [usuario.email, usuario.senha]);

        if (rows == null) {
            res.status(404).json({ message: "Não encontrado" });
        } else {
            res.status(200).json({ message: "existe" });
        }
        res.status(200).json({message: "existe"});
    } catch (error) {
        console.error("Erro ao buscar usuários:", error);
        res.status(500).json({ error: 'Ocorreu um erro ao buscar os dados' });
    }
});


export default router;