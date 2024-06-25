import express from 'express';
import { atualizarAgendamento, criarAgendamento, deletarAgendamento, obterAgendamentoPorId, obterTodosOsAgendamentos, obterTodosOsAgentesSaude, obterTodosOsIdosos } from '../controllers/agendamentoController';
import { cadastrarUsuario, login } from '../Auth/Auth';

const router = express.Router();

router.post('/agenda', criarAgendamento);

router.get('/agenda', obterTodosOsAgendamentos );

router.get('/agenda/:id', obterAgendamentoPorId );

router.put('/agenda/:id', atualizarAgendamento );

router.delete('/agenda/:id', deletarAgendamento );

router.get('/idoso', obterTodosOsIdosos );

router.get('/agentes',obterTodosOsAgentesSaude  );

router.post('/cadastro', cadastrarUsuario);

router.post('/login', login);
export default router;
