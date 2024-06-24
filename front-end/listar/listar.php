<?php
$url = "http://localhost:3000/api/agenda";

$ch = curl_init($url);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/json'));

$response = curl_exec($ch);

if(curl_errno($ch)) {
    echo 'Erro na requisição: ' . curl_error($ch);
    exit;
}

curl_close($ch);

$data = json_decode($response, true);

?>

<h1>Lista de Agendamentos</h1>
<table border="1">
    <tr>
        <th>Agente de Saude</th>
        <th>Idoso</th>
        <th>Data</th>
        <th>Hora</th>
        <th>Opções</th>
    </tr>
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
</table>
