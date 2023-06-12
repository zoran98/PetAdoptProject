import { useEffect, useState } from "react";
import { Button, ButtonGroup, Table } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import PetAdoptAxios from "../../apis/PetAdoptAxios";


const Pets = () => {
    const[pets, setPets] = useState([]);
    const[totalPages, setTotalPages] = useState(0)
    const[pageNo, setPageNo] = useState(0)
    const navigate = useNavigate()


    useEffect(() => {
        getPets(0);
    }, [])


    const getPets = (newPageNo) => {
        const conf = {
            params: {
                pageNo: newPageNo
            }
        }

        PetAdoptAxios.get("/ljubimci", conf)
            .then((res) => {
                console.log(res)
                setPageNo(newPageNo)
                setPets(res.data)
                setTotalPages(res.headers['total-pages'])
            })
            .catch((error) => {
                console.log(error)
                alert("Doslo je do greske prilikom ispisivanja ljubimaca!")
            })
    }

    const doDelete = (petId) => {
        PetAdoptAxios.delete("/ljubimci/" + petId)
        .then((res) => {
            console.log(res)
            var nextPage
            if(pageNo == totalPages - 1 && pets.length == 1){
                nextPage = pageNo - 1
            } else {
                nextPage = pageNo
            }
            getPets(nextPage);
        })
        .catch((error) => {
            console.log(error)
            alert("Doslo je do greske prilikom brisanja!")
        })
    }

    const goToAdd = () => {
        navigate("/pets/add")
    }

    const doVakcinisan = (param) => {
        if(param == true){
            return "Vakcinisan";
        } else {
            return "Nije vakcinisan";
        }
    }

    const doTezina = (param) => {
        return param + "kg";
    }

    return (
        <div>
            <h1>Ljubimci</h1>
            <ButtonGroup style={{marginTop: 25, float: "left"}}>
                <Button
                style={{margin: 3, width: 150}} onClick={() => goToAdd()}>
                    Kreiraj Ljubimca
                </Button>
            </ButtonGroup>
            <ButtonGroup style={{marginTop: 25, float: "right"}}>
                <Button
                style={{margin: 3, width: 90}}
                disabled={pageNo == 0} onClick={() => getPets(pageNo - 1)}>
                    Prethodna
                </Button>
                <Button
                style={{margin: 3, width: 90}}
                disabled={pageNo == totalPages - 1} onClick={() => getPets(pageNo + 1)}>
                    Sledeca
                </Button>
            </ButtonGroup>

            <Table bordered striped style={{marginTop: 5}}>
                <thead className="thead-dark">
                    <tr>
                        <th>Ime</th>
                        <th>Starost</th>
                        <th>Vakcinisan</th>
                        <th>Pol</th>
                        <th>Tezina(Kg)</th>
                        <th>Opis</th>
                        <th>Kategorija</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    {pets.map((pet) => {
                        return (
                            <tr key={pet.id}>
                                <td>{pet.ime}</td>
                                <td>{pet.starost}</td>
                                <td>{doVakcinisan(pet.vakcinisan)}</td>
                                <td>{pet.pol}</td>
                                <td>{doTezina(pet.tezina)}</td>
                                <td>{pet.opis}</td>
                                <td>{pet.kategorijaNaziv}</td>
                                <td>
                                    {window.localStorage['role'] == "ROLE_ADMIN" ?
                                        [<Button
                                            variant="danger"
                                            onClick={() => doDelete(pet.id)}
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

export default Pets;