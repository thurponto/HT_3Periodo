<?php
require_once "config.php";

// Obter dados do formulário
$username = $_POST['username'];
$password = $_POST['password'];
$email = $_POST['email'];

// Hash da senha para segurança
$hashedPassword = password_hash($password, PASSWORD_DEFAULT);

// Inserir dados do usuário no banco de dados
$sql = "INSERT INTO usuarios (username, password, email) 
        VALUES ('$username', '$hashedPassword', '$email')";

if ($conn->query($sql) === TRUE) {
  echo "Cadastro realizado com sucesso!";
  // Redirecionar para a página de login
  header("Location: login.php");
} else {
  echo "Erro: ". $sql. "<br>". $conn->error;
}

$conn->close();
?>