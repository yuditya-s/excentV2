<?php

require_once("koneksi.php");

$id_user = $_POST["id_user"];

$query_single_user = "SELECT * FROM table_pengguna WHERE id_user = '$id_user'";

$result = mysqli_query($conn, $query_single_user);

if(mysqli_num_rows($result) > 0){
	$row = mysqli_fetch_assoc($result);

	$response['email'] = $row['email'];
	$response['password'] = $row['password'];
	$response['nama_lengkap'] = $row['nama_lengkap'];
	$response['status'] = $row['status'];
	$response['nip_nis'] = $row['nip_nis'];
	$response['kelas'] = $row['kelas'];
	$response['gender'] = $row['gender'];
	$response['no_hp'] = $row['no_hp'];
	$response['img_user'] = $row['img_user'];
}
else {
	$response['value'] = "no_user";
	$response['message'] = "Pengguna tidak ditemukan";
}

echo json_encode($response);
mysqli_close($conn);
?>