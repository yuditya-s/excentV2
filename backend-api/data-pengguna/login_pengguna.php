<?php

require_once("koneksi.php");

$email = $_POST["email"];
$password = $_POST["password"];

$server_name = $_SERVER['SERVER_ADDR'];

$query_check_user = "SELECT id_user, nama_lengkap, status, img_user FROM table_pengguna WHERE email = '$email' and password = '$password'";

$result = mysqli_query($conn, $query_check_user);

if(mysqli_num_rows($result) > 0){
	$row = mysqli_fetch_assoc($result);

	$response['success'] = true;
	$response['message'] = "Pengguna berhasil login";

	$response['id_user'] = $row['id_user'];
	$response['nama_lengkap'] = $row['nama_lengkap'];
	$response['status'] = $row['status'];
	$response['img_user'] = $row['img_user'];
	
}
else {
	$response['success'] = false;
	$response['message'] = "Pengguna tidak terdaftar";
}

echo json_encode($response);
mysqli_close($conn);
?>