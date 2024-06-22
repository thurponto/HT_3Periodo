import { Router, Request, Response } from 'express';
import db from '../database/db';
const router = Router();

router.post("/idosos", async (req: Request, res: Response) => {
  const idoso = req.body;

  // Validate nome
  if (!idoso.nome || idoso.nome.trim() === '') {
    return res.status(400).json({ error: 'Nome é obrigatório' });
  }

  // Validate idade
  if (!idoso.idade || isNaN(idoso.idade) || idoso.idade < 0) {
    return res.status(400).json({ error: 'Idade é obrigatório e deve ser um número positivo' });
  }

  if (!idoso.alergias || !Array.isArray(idoso.alergias) || idoso.alergias.length === 0) {
    return res.status(400).json({ error: 'Alergias é obrigatório' });
  }

  // Validate condicoes_medicas
  if (!idoso.condicoes_medicas || !Array.isArray(idoso.condicoes_medicas) || idoso.condicoes_medicas.length === 0) {
    return res.status(400).json({ error: 'Condições médicas é obrigatório' });
  }

  try {
    const [rows] = await db.execute('INSERT INTO idosos (nome, idade, alergias, condicoes_medicas) VALUES (?,?,?,?)', [
      idoso.nome,
      idoso.idade,
      JSON.stringify(idoso.alergias),
      JSON.stringify(idoso.condicoes_medicas)
    ]);

    res.status(201).json({ message: 'registrado com sucesso' });
  } catch (error) {
    console.error("Erro ao criar idoso:", error);
    res.status(500).json({ error: 'Ocorreu um erro ao registrar' });
  }
});

export default router;