<?php
require 'config.php';

$request = $_SERVER['REQUEST_URI'];

switch ($request) {
    case '/' :
        require __DIR__ . '/home.php';
        break;
    case '/login' :
        require __DIR__ . '/login.php';
        break;
    case '/register' :
        require __DIR__ . '/register.php';
        break;
    case '/logout' :
        require __DIR__ . '/logout.php';
        break;
    default:
        http_response_code(404);
        echo "Página não encontrada.";
        break;
}
?>
