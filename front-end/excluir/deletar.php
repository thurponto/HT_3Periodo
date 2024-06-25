<?php
$id = $_GET['id'] ?? NULL;

if ($id) {
    $url = "http://localhost:3000/api/agenda/$id";
    $ch = curl_init($url);

    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "DELETE");
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/json'));

    $response = curl_exec($ch);

    if (curl_errno($ch)) {
        echo 'Erro ao excluir o idoso.';
    } else {
        header('Location: ?action=listar');
        echo 'Idoso excluído com sucesso.';
    }

    curl_close($ch);
} else {
    echo 'ID inválido.';
}
?>
