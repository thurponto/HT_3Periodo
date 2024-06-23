<?php
session_start();
require_once 'config.php';

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
  $name = $_POST['name'];
  $email = $_POST['email'];
  $password = password_hash($_POST['password'], PASSWORD_BCRYPT);
  $cpf = $_POST['cpf'];
  $dob = $_POST['dob'];
  $gender = $_POST['gender'];

  $query = "INSERT INTO users (name, email, password, cpf, dob, gender) VALUES (?, ?, ?, ?, ?, ?)";
  $stmt = $conn->prepare($query);
  $stmt->bind_param("ssssss", $name, $email, $password, $cpf, $dob, $gender);

  if ($stmt->execute()) {
    $_SESSION['user_id'] = $stmt->insert_id;
    $_SESSION['username'] = $name;
    header('Location: index.php');
  } else {
    echo "Erro: " . $stmt->error;
  }

  $stmt->close();
  $conn->close();
}
?>
