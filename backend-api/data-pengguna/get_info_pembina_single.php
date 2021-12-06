<?php
require("koneksi.php");


if($_SERVER['REQUEST_METHOD'] == 'POST'){

	$id_user = $_POST['id_user'];

	$query_user = "SELECT nama_lengkap, kelas, no_hp, img_user FROM table_pengguna WHERE id_user = '$id_user'";
	$result = mysqli_query($conn, $query_user);

	if(mysqli_num_rows($result) > 0){
		
		$row = mysqli_fetch_assoc($result);

		$response['nama_lengkap'] = $row['nama_lengkap'];
		$response['kelas'] = $row['kelas'];
		$response['no_hp'] = $row['no_hp'];
		$response['img_user'] = $row['img_user'];
	} else {
		$response['nama_lengkap'] = "Tidak ditemukan";
		$response['kelas'] = "Tidak ditemukan";
		$response['no_hp']  = "Tidak ditemukan";
		$response['img_user'] = "data-pengguna/img_user/noProfilePicture.jpeg";
	}
	
}

echo json_encode($response);
mysqli_close($conn);
?>