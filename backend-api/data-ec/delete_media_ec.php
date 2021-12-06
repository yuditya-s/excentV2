<?php

require_once 'koneksi.php';


$id_media = $_POST['id_media'];
$media_path = $_POST['media_path'];

if($_SERVER['REQUEST_METHOD'] == 'POST'){
	$query_delete_media = "DELETE FROM table_media WHERE id_media = '$id_media'";
	$cek = mysqli_query($conn, $query_delete_media);
	if($cek > 0){
		$media_path_part = explode("/", $media_path);
		$media_path_4delete = $media_path_part[1]."/".$media_path_part[2];
		$hapus = unlink($media_path_4delete);
		if($hapus)	{
			$response["success"] = true;
			$response["message"] = "Image dan data berhasil dihapus";
		} else {
			$response["success"] = false;
			$response["message"] = "Image dan data tidak berhasil dihapus";
			} 
		}
		else {
			$response["success"] = false;
			$response["message"] = "Data tidak dapat dihapus";
		}

	} else {
		$response["success"] = false;
			$response["message"] = "Error!: ".mysqli_error($conn);
	}
	echo json_encode(($response));
	mysqli_close($conn);
	?>
