<?php

require_once 'koneksi.php';

if($_SERVER['REQUEST_METHOD'] == 'POST'){

	$id_ec = $_POST['id_ec'];
	$name_ec = $_POST['name_ec'];
	$desc_ec = $_POST['desc_ec'];
	$reg_question = $_POST['reg_question'];

	$query_update_ec = "UPDATE table_ec 
						SET name_ec='$name_ec',
						desc_ec='$desc_ec',
						reg_question = '$reg_question'
						WHERE id_ec= '$id_ec'";
	$eksekusi = mysqli_query($conn, $query_update_ec);
	$cek = mysqli_affected_rows($conn);

	if($cek > 0){
		$response['success'] = true;
		$response['message'] = "Info dasar Ekstrakurikular berhasil diperbarui";
	} else {
		$response['success'] = false;
		$response['message'] = "Info dasar Ekstrakurikular tidak dapat diperbarui";
	}
echo json_encode($response);
mysqli_close($conn); 
}
?>