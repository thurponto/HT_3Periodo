<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Listar Agendamentos</title>
    <link rel="stylesheet" href="../css/lista.css">
</head>
<body>
    <header>
        <div class="logo">
            <img src="../imagens/logo2.png" alt="Logo">
            <h1>Vaccin Clinica</h1>
        </div>
        <nav>
            <ul>
                <li><a href="home.php" class="button">Página Principal</a></li>
                <li><a href="agenda.php" class="button">Criar Agendamento</a></li>
            </ul>
        </nav>
    </header>
    <div class="main-content">
        <h2>Listar Agendamentos</h2>
        <?php
        $url = "http://localhost:3000/api/agenda";

        $ch = curl_init($url);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/json'));

        $response = curl_exec($ch);

        if(curl_errno($ch)) {
            echo '<p>Erro na requisição: ' . curl_error($ch) . '</p>';
            exit;
        }

        curl_close($ch);

        $data = json_decode($response, true);
        ?>
        <table class="table-agendamentos">
            <thead>
                <tr>
                    <th>Agente de Saúde</th>
                    <th>Idoso</th>
                    <th>Data</th>
                    <th>Hora</th>
                    <th>Opções</th>
                </tr>
            </thead>
            <tbody>
                <?php if (!empty($data) && is_array($data)): ?>
                    <?php foreach ($data as $agendamento): ?>
                        <tr>
                            <td><?php echo htmlspecialchars($agendamento['nomeAgenteSaude']); ?></td>
                            <td><?php echo htmlspecialchars($agendamento['nomeIdoso'] ?? ''); ?></td>
                            <td><?php echo htmlspecialchars($agendamento['data']); ?></td>
                            <td><?php echo htmlspecialchars($agendamento['horario'] ?? ''); ?></td>
                            <td>
                                <a href="index.php?param=atualizar/editar&id=<?php echo htmlspecialchars($agendamento['id']); ?>">Editar</a>
                                <a href="index.php?param=excluir/deletar&id=<?php echo htmlspecialchars($agendamento['id']); ?>">Deletar</a>
                            </td>
                        </tr>
                    <?php endforeach; ?>
                <?php else: ?>
                    <tr>
                        <td colspan="5">Nenhum agendamento encontrado.</td>
                    </tr>
                <?php endif; ?>
            </tbody>
        </table>
    </div>
</body>
</html>
