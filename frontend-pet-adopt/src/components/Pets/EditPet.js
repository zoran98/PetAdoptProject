import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import PetAdoptAxios from "../../apis/PetAdoptAxios";
import { Button, Form, FormControl, FormGroup, FormLabel } from "react-bootstrap";


const EditPet = () => {

    const [pet, setPet] = useState({})
    const [categories, setCategories] = useState([])
    const navigate = useNavigate()
    const routeParams = useParams()

    useEffect(() => {
        getData();
    }, [])

    const getData = () => {
        getCategories();
        getPet();
    }

    const getPet = () => {
        PetAdoptAxios.get("/ljubimci/" + routeParams.id)
        .then((res) => {
            setPet(res.data)
        })
        .catch(() => {
            alert("Nije uspelo dodavanje ljubimca!")
        })
    }

    const getCategories = () => {
        PetAdoptAxios.get("/kategorije")
        .then((res) => {
            setCategories(res.data)
        })
        .catch(() => {
            alert("Nije uspelo dodavanje!")
        })
    }

    const doEdit = () => {
        PetAdoptAxios.put("/ljubimci/" + routeParams.id, pet)
        .then((res) => {
            navigate("/pets");
        })
        .catch(() => {
            alert("Nije uspela izmena!")
        })
    }

    const valueInputChange = (e) => {
        let editetPet = {...pet};

        const name = e.target.name;
        const value = e.target.value;

        editetPet[name] = value;
        setPet(editetPet);
    }

    return (
        <div>
            <h1>Ljubimac</h1>

            <Form>
                <FormGroup>
                    <FormLabel>Ime</FormLabel>
                    <FormControl
                    onChange={(e) => valueInputChange(e)}
                    name="ime"
                    value={pet.ime}
                    as="input"
                    ></FormControl>
                </FormGroup>

                <FormGroup>
                    <FormLabel>Starost</FormLabel>
                    <FormControl
                    onChange={(e) => valueInputChange(e)}
                    name="starost"
                    value={pet.starost}
                    as="input"
                    ></FormControl>
                </FormGroup>

                <FormGroup>
                    <FormLabel>Vakcinisan</FormLabel>
                    <FormControl
                    onChange={(e) => valueInputChange(e)}
                    name="vakcinisan"
                    value={pet.vakcinisan}
                    as="input"
                    ></FormControl>
                </FormGroup>

                <FormGroup>
                    <FormLabel>Pol</FormLabel>
                    <FormControl
                    onChange={(e) => valueInputChange(e)}
                    name="pol"
                    value={pet.pol}
                    as="input"
                    ></FormControl>
                </FormGroup>

                <FormGroup>
                    <FormLabel>Tezina</FormLabel>
                    <FormControl
                    onChange={(e) => valueInputChange(e)}
                    name="tezina"
                    value={pet.tezina}
                    as="input"
                    ></FormControl>
                </FormGroup>

                <FormGroup>
                    <FormLabel>Opis</FormLabel>
                    <FormControl
                    onChange={(e) => valueInputChange(e)}
                    name="opis"
                    value={pet.opis}
                    as="input"
                    ></FormControl>
                </FormGroup>

                <FormGroup>
                    <FormLabel>Kategorija</FormLabel>
                    <FormControl
                    onChange={(e) => valueInputChange(e)}
                    name="kategorijaId"
                    value={pet.kategorijaId}
                    as="select"
                    >
                        <option value={-1}></option>
                        {categories.map((cat) => {
                            return (
                                <option value={cat.id} key={cat.id}>
                                    {cat.naziv}
                                </option>
                            );
                        })}
                    </FormControl>
                </FormGroup>

                <Button variant="primary" onClick={() => doEdit()}>
                    Edit
                </Button>
            </Form>
        </div>
    )

}

export default EditPet;