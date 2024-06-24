import { Request, Response, NextFunction } from 'express';
import { QueryTypes } from 'sequelize';
import sequelize from '../database/database';


const handleError = (res: Response, error: unknown) => {
  console.error('Erro:', error);
  res.status(500).json({ error: 'Erro interno do servidor' });
};


export const criarAgendamento = async (req: Request, res: Response, next: NextFunction) => {
  const { agenteSaudeId, idosoId, data, horario } = req.body;
  try {
    if (!agenteSaudeId || !idosoId || !data || !horario) {
      return res.status(400).json({ error: 'AgenteSaudeId, IdosoId, data e horario são obrigatórios' });
    }
    const result = await sequelize.query(
      'INSERT INTO agenda (agente_saude_id, idoso_id, data, horario) VALUES (?, ?, ?, ?)',
      {
        replacements: [agenteSaudeId, idosoId, data, horario],
        type: QueryTypes.INSERT,
      }
    );
    res.status(201).json({ id: (result as any)[0] });
  } catch (error) {
    handleError(res, error);
  }
};

export const obterTodosOsAgendamentos = async (req: Request, res: Response, next: NextFunction) => {
  try {
    const agendamentos = await sequelize.query(`
      SELECT a.id, a.data, a.horario, ag.nome AS nomeAgenteSaude, i.nome AS nomeIdoso
      FROM agenda AS a
      INNER JOIN agente_saude AS ag ON a.agente_saude_id = ag.id
      INNER JOIN idoso AS i ON a.idoso_id = i.id
    `, {
      type: QueryTypes.SELECT
    });
    
    res.status(200).json(agendamentos);
  } catch (error) {
    console.error('Erro:', error);
    res.status(500).json({ error: 'Erro interno do servidor' });
  }
};



export const obterAgendamentoPorId = async (req: Request, res: Response, next: NextFunction) => {
  const id = req.params.id;
  try {
    const agendamento = await sequelize.query(`
      SELECT a.id, a.data, a.horario, ag.nome AS nomeAgenteSaude, i.nome AS nomeIdoso
      FROM agenda AS a
      INNER JOIN agente_saude AS ag ON a.agente_saude_id = ag.id
      INNER JOIN idoso AS i ON a.idoso_id = i.id
      WHERE a.id = ?
    `, {
      replacements: [id],
      type: QueryTypes.SELECT,
    });
    
    if (agendamento.length > 0) {
      res.status(200).json(agendamento[0]);
    } else {
      res.status(404).json({ error: 'Agendamento não encontrado' });
    }
  } catch (error) {
    console.error('Erro:', error);
    res.status(500).json({ error: 'Erro interno do servidor' });
  }
};


export const atualizarAgendamento = async (req: Request, res: Response, next: NextFunction) => {
  const id = req.params.id;
  const { data, horario } = req.body;

  try {
    const result = await sequelize.query(
      'UPDATE agenda SET data = ?, horario = ? WHERE id = ?',
      {
        replacements: [data, horario, id],
        type: QueryTypes.UPDATE,
      }
    );

    if (result && result[0] as any> 0) {
      res.status(200).json({ message: 'Agendamento atualizado com sucesso' });
    } else {
      res.status(404).json({ error: 'Agendamento não encontrado' });
    }
  } catch (error) {
    handleError(res, error);
  }
};



export const deletarAgendamento = async (req: Request, res: Response, next: NextFunction) => {
  const id = req.params.id;
  try {
    const result = await sequelize.query('DELETE FROM agenda WHERE id = ?', {
      replacements: [id],
      type: QueryTypes.DELETE,
    });
    if ((result as any)[0] > 0) {
      res.status(204).send();
    } else {
      res.status(500).json({ error: 'Agendamento não encontrado' });
    }
  } catch (error: any) {
    if (error.code === 'ER_ROW_IS_REFERENCED_2') {
      res.status(400).json({ error: 'Não é possível excluir o agendamento porque há um histórico agendado.' });
    } else {
      handleError(res, error);
    }
  }
}

export const obterTodosOsIdosos = async (req: Request, res: Response, next: NextFunction) => {
  try {
    const idosos = await sequelize.query('SELECT id, nome FROM idoso', {
      type: QueryTypes.SELECT
    });
    res.status(200).json(idosos);
  } catch (error) {
    console.error('Erro:', error);
    res.status(500).json({ error: 'Erro interno do servidor' });
  }
};

export const obterTodosOsAgentesSaude = async (req: Request, res: Response, next: NextFunction) => {
  try {
    const agentesSaude = await sequelize.query('SELECT id, nome FROM agente_saude', {
      type: QueryTypes.SELECT
    });
    res.status(200).json(agentesSaude);
  } catch (error) {
    console.error('Erro:', error);
    res.status(500).json({ error: 'Erro interno do servidor' });
  }
};
