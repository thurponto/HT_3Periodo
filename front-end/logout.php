<?php
session_start();

// Destruir sessão
session_unset();
session_destroy();

// Redirecionar para a página de login
header("Location: login.php");
?>