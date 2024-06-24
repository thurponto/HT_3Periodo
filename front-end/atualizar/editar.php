<?php
$id = $_GET['id'] ?? NULL;

if ($id) {
    $url = "http://localhost:3000/api/agenda/$id";
    $ch = curl_init($url);

    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/json'));

    $response = curl_exec($ch);

    if (curl_errno($ch)) {
        echo 'Erro ao fazer a requisição: ' . curl_error($ch);
    } else {
        $agendamento = json_decode($response, true);
    }

    curl_close($ch);
} else {
    header("Location: listar.php");
    exit();

}
if ($_POST) {
    $data = [
        "data" => $_POST["data"],
        "horario" => $_POST["hora"],
    ];

    $url = "http://localhost:3000/api/agendamentos/$id";
    $ch = curl_init($url);

    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "PUT");
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/json'));
    curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($data));

    $response = curl_exec($ch);

    if (curl_errno($ch)) {
        echo 'Erro ao fazer a requisição: ' . curl_error($ch);
    } else {
        header('Location: ?action=listar');
        exit();
    }

    curl_close($ch);
}
?>

<div>
    <form action="" method="POST">
        <label for="paciente">Nome do Paciente</label>
        <input type="text" class="form-control" name="paciente" required value="<?= $agendamento['nomeIdoso'] ?? '' ?>" disabled>
        <label for="paciente">Nome do Agente de Saúde</label>
        <input type="text" class="form-control" name="paciente" required value="<?= $agendamento['nomeAgenteSaude'] ?? '' ?>" disabled>

        <label for="data">Data</label>
        <input type="date" class="form-control" name="data" required value="<?= $agendamento['data'] ?? '' ?>">

        <label for="hora">Hora</label>
        <input type="time" class="form-control" name="hora" required value="<?= $agendamento['horario'] ?? '' ?>">

        <br>
        <button type="submit" class="btn btn-success">Salvar</button>
    </form>
</div>
