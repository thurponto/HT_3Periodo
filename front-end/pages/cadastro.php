<?php
if ($_POST) {
    $nome = $_POST["nome"] ?? NULL;
    $telefone = $_POST["telefone"] ?? NULL;
    $cpf = $_POST["cpf"] ?? NULL;
    $dataNascimento = $_POST["dataNascimento"] ?? NULL;
    $sexo = $_POST["sexo"] ?? NULL;
    $cep = $_POST["cep"] ?? NULL;
    $senha = $_POST["senha"] ?? NULL;

    $dataNascimentoFormatted = date('Y-m-d', strtotime($dataNascimento));

    $url = 'http://localhost:3000/api/users';
    $data = json_encode(array(
        "nome" => $nome,
        "telefone" => $telefone,
        "cpf" => $cpf,
        "dataNascimento" => $dataNascimentoFormatted,
        "sexo" => $sexo,
        "cep" => $cep,
        "senha" => $senha
    ));

    $curl = curl_init($url);
    curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($curl, CURLOPT_POST, true);
    curl_setopt($curl, CURLOPT_POSTFIELDS, $data);
    curl_setopt($curl, CURLOPT_HTTPHEADER, array('Content-Type: application/json'));

    $response = curl_exec($curl);
    $httpcode = curl_getinfo($curl, CURLINFO_HTTP_CODE);

    curl_close($curl);

    if ($httpcode == 201) {
        echo "<script>alert('Usuário cadastrado com sucesso!');</script>";
    } else {
        echo "<script>alert('Erro ao cadastrar usuário. Por favor, tente novamente mais tarde.');</script>";
    }
}
?>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastro de Usuário</title>
    <link rel="stylesheet" href="./css/cadastro.css">
</head>
<body>
    <div class="cadastro">
        <img src="./imagens/logo1.png" alt="Logo" class="logo">
        <h1>Cadastro de Usuário</h1>
        <form id="formCadastro" method="POST">
            <label for="nome">Nome:</label>
            <input type="text" id="nome" name="nome" required>

            <label for="telefone">Telefone:</label>
            <input type="text" id="telefone" name="telefone" required>

            <label for="cpf">CPF:</label>
            <input type="text" id="cpf" name="cpf" required>

            <label for="dataNascimento">Data de Nascimento:</label>
            <input type="date" id="dataNascimento" name="dataNascimento" required>

            <label for="sexo">Sexo:</label>
            <select id="sexo" name="sexo" required>
                <option value="">Selecione</option>
                <option value="M">Masculino</option>
                <option value="F">Feminino</option>
            </select>

            <label for="cep">CEP:</label>
            <input type="text" id="cep" name="cep" required>

            <label for="senha">Senha:</label>
            <input type="password" id="senha" name="senha" required>

            <button type="submit">Cadastrar</button>
        </form>
        <div class="login-link">
            <p>Já tem uma conta? <a href="pages/login">Login</a></p>
        </div>
    </div>
</body>
</html>
