<?php
require "elfme_koneksi.php";

$name = $_POST["name"];
$username = $_POST["username"];
$password = $_POST["password"];
$email = $_POST["email"];
$ponsel = $_POST["ponsel"];
$alamat = $_POST["alamat"];
$mysql_qry = "insert into register(nama,username,password,email,ponsel,alamat) values ('$name','$username','$password','$email','$ponsel','$alamat')";

if($conn->query($mysql_qry) === TRUE){
	echo "Register sukses";
}else{
	echo "some_error";
	echo "ERROR : " . $mysql_qry . "</br>" . $conn->error;
}

$conn->close();
?>