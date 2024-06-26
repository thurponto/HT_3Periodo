<?php
function buscarIdosos($url) {
    $ch = curl_init($url);
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    $response = curl_exec($ch);
    
    if (curl_errno($ch)) {
        echo 'Erro ao fazer a requisição: ' . curl_error($ch);
        return false;
    }
    
    $http_code = curl_getinfo($ch, CURLINFO_HTTP_CODE);
    if ($http_code !== 200) {
        echo 'Erro: Status do HTTP não foi 200';
        return false;
    }
    
    $dados = json_decode($response, true);
    //if (!$dados) {
       // echo 'Formato de dados inválido.';
       // return false;
    //}

    curl_close($ch);
    
    return $dados;
}

function buscarAgentesSaude($url) {
    $ch = curl_init($url);
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    $response = curl_exec($ch);
    
    if (curl_errno($ch)) {
        echo 'Erro ao fazer a requisição: ' . curl_error($ch);
        return false;
    }
    
    $http_code = curl_getinfo($ch, CURLINFO_HTTP_CODE);
    if ($http_code !== 200) {
        echo 'Erro: Status do HTTP não foi 200';
        return false;
    }
    
    $dados = json_decode($response, true);
    //if (!$dados) {
       // echo 'Formato de dados inválido.';
       // return false;
    //}

    curl_close($ch);
    
    return $dados;
}

if ($_POST) {
    $paciente = $_POST['paciente'] ?? NULL;
    $agenteSaude = $_POST['agenteSaude'] ?? NULL;
    $data = $_POST['data'] ?? NULL;
    $hora = $_POST['hora'] ?? NULL;

    $data_agendamento = [
        'idosoId' => $paciente,
        'agenteSaudeId' => $agenteSaude,
        'data' => $data,
        'horario' => $hora,
    ];

    $url_agendamentos = 'http://localhost:3000/api/agenda';
    $ch = curl_init($url_agendamentos);

    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($ch, CURLOPT_POST, true);
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/json'));
    curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($data_agendamento));

    $response = curl_exec($ch);

    if (curl_errno($ch)) {
        echo 'Erro ao fazer a requisição: ' . curl_error($ch);
    } else {
        header('Location: lista');
    }

    curl_close($ch);
}

$url_idosos = 'http://localhost:3000/api/idoso';
$url_agentes_saude = 'http://localhost:3000/api/agentes';

$dados_idosos = buscarIdosos($url_idosos);
$dados_agentes_saude = buscarAgentesSaude($url_agentes_saude);

//if (!$dados_idosos || !is_array($dados_idosos)) {
    //exit('Erro ao buscar dados dos idosos.');
//}

//if (!$dados_agentes_saude || !is_array($dados_agentes_saude)) {
   // exit('Erro ao buscar dados dos agentes de saúde.');
//}
?>

<link rel="stylesheet" href="./css/agenda.css">
<header>
        <div class="logo">
            <img src="./imagens/logo2.png" alt="Logo">
            <h1>Vaccin Clinica</h1>
        </div>
        <nav>
            <ul>
                <li>
                    <a href="?logout">Deslogar</a>
                </li>
                <li>
                    <a href="pages/vacinas">Importância das vacinas</a>
                </li>
            </ul>
        </nav>
    </header>
<div>
    <a href="pages/home" class="button">Página Principal</a>
    <form action="" method="POST">
        <label for="paciente">Nome do Paciente</label>
        <select name="paciente" class="form-control" required>
            <?php foreach ($dados_idosos as $idoso): ?>
                <option value="<?= $idoso['id'] ?>"><?= $idoso['nome'] ?></option>
            <?php endforeach; ?>
        </select>
        <label for="agenteSaude">Agente de Saúde</label>
        <select name="agenteSaude" class="form-control" required>
            <?php foreach ($dados_agentes_saude as $agente): ?>
                <option value="<?= $agente['id'] ?>"><?= $agente['nome'] ?></option>
            <?php endforeach; ?>
        </select>
        <label for="data">Data</label>
        <input type="date" class="form-control" name="data" required>
        <label for="hora">Hora</label>
        <input type="time" class="form-control" name="hora" required>
        <br>
        <button type="submit" class="btn btn-success">Cadastrar</button>
    </form>
</div>
