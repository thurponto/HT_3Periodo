<?php
require 'config.php';

$username = $_POST['username'];
$password = $_POST['password'];

$response = file_get_contents(API_URL . '/login', false, stream_context_create([
    'http' => [
        'method' => 'POST',
        'header' => 'Content-type: application/json',
        'content' => json_encode(['username' => $username, 'password' => $password]),
    ]
]));

$result = json_decode($response, true);

if ($result['success']) {
    $_SESSION['user_id'] = $result['user_id'];
    $_SESSION['username'] = $username;
    header('Location: /');
} else {
    echo "Login falhou: " . $result['message'];
}
?>
