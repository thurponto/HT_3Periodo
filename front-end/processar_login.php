<?php
require_once "config.php";

// Obter dados do formulário
$username = $_POST['username'];
$password = $_POST['password'];

// Consultar banco de dados para verificar usuário
$sql = "SELECT * FROM usuarios WHERE username='$username'";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
  $row = $result->fetch_assoc();
  // Verificar senha
  if (password_verify($password, $row['password'])) {
    // Iniciar sessão
    session_start();
    $_SESSION['user_id'] = $row['id'];
    $_SESSION['username'] = $row['username'];

    // Redirecionar para a página inicial
    header("Location: index.php");
  } else {
    echo "Senha incorreta.";
  }
} else {
  echo "Usuário não encontrado.";
}

$conn->close();
?>