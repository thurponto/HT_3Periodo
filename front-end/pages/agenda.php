<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Criar Agendamento</title>
    <link rel="stylesheet" href="../css/agenda.css">
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
                <li><a href="lista.php" class="button">Listar Agendamentos</a></li>
            </ul>
        </nav>
    </header>
    <div class="main-content">
        <h2>Criar Agendamento</h2>
        <form action="agendar.php" method="post" class="form-agendamento">
            <div class="form-group">
                <label for="paciente">Nome do Paciente</label>
                <select name="paciente" class="form-control" required>
                    <?php
                    include '../cadastrar/agendar.php';
                    $dados_idosos = buscarIdosos('http://localhost:3000/api/idosos');
                    foreach ($dados_idosos as $idoso) {
                        echo '<option value="' . $idoso['id'] . '">' . $idoso['nome'] . '</option>';
                    }
                    ?>
                </select>
            </div>
            <div class="form-group">
                <label for="agenteSaude">Agente de Saúde</label>
                <select name="agenteSaude" class="form-control" required>
                    <?php
                    $dados_agentes_saude = buscarAgentesSaude('http://localhost:3000/api/agentes-saude');
                    foreach ($dados_agentes_saude as $agente) {
                        echo '<option value="' . $agente['id'] . '">' . $agente['nome'] . '</option>';
                    }
                    ?>
                </select>
            </div>
            <div class="form-group">
                <label for="data">Data</label>
                <input type="date" class="form-control" name="data" required>
            </div>
            <div class="form-group">
                <label for="hora">Hora</label>
                <input type="time" class="form-control" name="hora" required>
            </div>
            <br>
            <button type="submit" class="btn btn-success">Cadastrar</button>
        </form>
    </div>
</body>
</html>
