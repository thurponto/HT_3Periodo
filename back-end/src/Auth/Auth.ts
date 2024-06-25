import { Request, Response, NextFunction } from 'express';
import jwt from 'jsonwebtoken';
import bcrypt from 'bcryptjs';
import User from '../model/UserModel';

const handleError = (res: Response, error: unknown) => {
  console.error('Erro:', error);
  res.status(500).json({ error: 'Erro interno do servidor' });
};

export const cadastrarUsuario = async (req: Request, res: Response, next: NextFunction) => {
  const { nome, telefone, cpf, dataNascimento, sexo, cep, senha } = req.body;
  try {
    if (!nome || !telefone || !cpf || !dataNascimento || !sexo || !cep || !senha) {
      return res.status(400).json({ error: 'Todos os campos são obrigatórios' });
    }

    const existingUser = await User.findOne({ where: { cpf } });
    if (existingUser) {
      return res.status(400).json({ error: 'Usuário já cadastrado com este CPF' });
    }

    const hashedPassword = await bcrypt.hash(senha, 10);
    const newUser = await User.create({ nome, telefone, cpf, dataNascimento, sexo, cep, senha: hashedPassword });

    res.status(201).json({ id: newUser.id, nome: newUser.nome });
  } catch (error) {
    handleError(res, error);
  }
};

export const login = async (req: Request, res: Response, next: NextFunction) => {
  const { cpf, dataNascimento } = req.body;
  try {
    if (!cpf || !dataNascimento) {
      return res.status(400).json({ error: 'CPF e data de nascimento são obrigatórios' });
    }

    const user = await User.findOne({ where: { cpf } });
    if (!user) {
      return res.status(401).json({ error: 'Credenciais inválidas' });
    }

    const userDateOfBirth = new Date(user.dataNascimento).toISOString().split('T')[0];
    if (userDateOfBirth !== dataNascimento) {
      return res.status(401).json({ error: 'Credenciais inválidas' });
    }

    const token = jwt.sign({ userId: user.id }, 'secretpassword', { expiresIn: '1h' });

    res.status(200).json({ token });
  } catch (error) {
    handleError(res, error);
  }
}

export const verifyToken = (req: Request, res: Response, next: NextFunction) => {
  const token = req.headers.authorization?.split(' ')[1];
  if (!token) {
    return res.status(401).json({ error: 'Token não fornecido' });
  }

  jwt.verify(token, 'secretpassword', (err: jwt.VerifyErrors | null, decoded: any) => {
    if (err) {
      return res.status(401).json({ error: 'Token inválido' });
    }
    req.body.userId = decoded.userId;
    next();
  });
};
