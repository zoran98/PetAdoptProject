import { useEffect, useState } from "react";
import PetAdoptAxios from "../../apis/PetAdoptAxios";
import { Button, ButtonGroup, Table } from "react-bootstrap";
import { useNavigate } from "react-router-dom";



const Category = () => {
    const[category, setCategory] = useState([]);
    const[totalPages, setTotalPages] = useState(0)
    const[pageNo, setPageNo] = useState(0)
    const navigate = useNavigate()


    useEffect(() => {
        getCategories(0);
    }, [])


    const getCategories = (newPageNo) => {
        const conf = {
            params: {
                pageNo: newPageNo
            }
        }

        PetAdoptAxios.get("/kategorije", conf)
            .then((res) => {
                console.log(res)
                setPageNo(newPageNo)
                setCategory(res.data)
                setTotalPages(res.headers['total-pages'])
            })
            .catch((error) => {
                console.log(error)
                alert("Doslo je do greske prilikom ispisivanja kategorija!")
            })
    }

    const doDelete = (categoryId) => {
        PetAdoptAxios.delete("/kategorije/" + categoryId)
        .then((res) => {
            console.log(res)
            var nextPage
            if(pageNo == totalPages - 1 && category.length == 1){
                nextPage = pageNo - 1
            } else {
                nextPage = pageNo
            }
            getCategories(nextPage);
        })
        .catch((error) => {
            console.log(error)
            alert("Doslo je do greske prilikom brisanja!")
        })
    }

    const goToAdd = () => {
        navigate("/category/add")
    }

    return (
        <div>
            <h1>Kategorije</h1>
            <ButtonGroup style={{marginTop: 25, float: "left"}}>
                <Button
                style={{margin: 3, width: 150}} onClick={() => goToAdd()}>
                    Kreiraj Kategoriju
                </Button>
            </ButtonGroup>
            <ButtonGroup style={{marginTop: 25, float: "right"}}>
                <Button
                style={{margin: 3, width: 90}}
                disabled={pageNo == 0} onClick={() => getCategories(pageNo - 1)}>
                    Prethodna
                </Button>
                <Button
                style={{margin: 3, width: 90}}
                disabled={pageNo == totalPages - 1} onClick={() => getCategories(pageNo + 1)}>
                    Sledeca
                </Button>
            </ButtonGroup>

            <Table bordered striped style={{marginTop: 5}}>
                <thead className="thead-dark">
                    <tr>
                        <th>Naziv</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    {category.map((cat) => {
                        return (
                            <tr key={cat.id}>
                                <td>{cat.naziv}</td>
                                <td>
                                    {window.localStorage['role'] == "ROLE_ADMIN" ?
                                        [<Button
                                            variant="danger"
                                            onClick={() => doDelete(cat.id)}
                                            style={{marginLeft: 5}}>
                                        Delete
                                        </Button>] : null}
                                </td>
                            </tr>
                        );
                    })}
                </tbody>
            </Table>
        </div>
    )
}

export default Category;