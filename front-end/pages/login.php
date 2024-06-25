<?php
if ($_POST) {
    $cpf = $_POST["cpf"] ?? NULL;
    $dataNascimento = $_POST["dataNascimento"] ?? NULL;

    $url = 'http://localhost:3000/api/login';
    $data = json_encode(array("cpf" => $cpf, "dataNascimento" => $dataNascimento));

    $curl = curl_init($url);
    curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($curl, CURLOPT_POST, true);
    curl_setopt($curl, CURLOPT_POSTFIELDS, $data);
    curl_setopt($curl, CURLOPT_HTTPHEADER, array('Content-Type: application/json'));

    $response = curl_exec($curl);
    $httpcode = curl_getinfo($curl, CURLINFO_HTTP_CODE);

    curl_close($curl);

    if ($httpcode == 200) {

        header("Location: ?param=pages/home");
    } else {
        echo "<script>alert('Erro: CPF ou data de nascimento incorretos');</script>";
    }
}
?>



<link rel="stylesheet" href="./css/login.css">
<div class="login">
<img src="./imagens/logo2.png" alt="Logo" class="logo">
<h1 class="text-center">Efetuar Login</h1>
    <form method="POST">
        <label for="cpf">CPF:</label>
        <input type="text" name="cpf" id="cpf" class="form-control" required placeholder="Digite seu CPF">

        <label for="dataNascimento">Data de Nascimento:</label>
        <input type="date" name="dataNascimento" id="dataNascimento" class="form-control" required>

        <button type="submit" class="btn btn-success w-100">Efetuar Login</button>
    </form>
    <div class="cadastro-link text-center">
        <p>Não possui uma conta? <a href="pages/cadastro">Cadastre-se</a></p>
    </div>
</div>

</body>
</html>
