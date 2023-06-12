import { useEffect, useState } from "react"
import { useNavigate } from "react-router-dom"
import PetAdoptAxios from "../../apis/PetAdoptAxios"
import { Button, Form } from "react-bootstrap"


const Add = () => {

    const emptyPet = {
        ime: "",
        starost: "",
        vakcinisan: false,
        pol: "",
        tezina: "",
        opis: "",
        kategorijaId: -1
    }

    const [pet, setPet] = useState(emptyPet)
    const [category, setCategory] = useState([])
    const navigate = useNavigate()

    useEffect(() => {
        getData();
    }, [])

    const getData = () => {
        getCategory();
    }

    const getCategory = () => {
        PetAdoptAxios.get("/kategorije")
        .then((res) => {
            console.log(res)
            setCategory(res.data)
        })
        .catch((error) => {
            console.log(error)
            alert("Doslo je do greske prilikom dodavanja kategorija!")
        })
    }

    const doAdd = () => {
        PetAdoptAxios.post("/ljubimci/" + pet)
        .then((res) => {
            console.log(res)
            setPet(pet)
            navigate("/pets")
        })
        .catch((error) => {
            console.log(error)
            alert("Nije uspelo dodavanje ljubimca!")
        })
    }

    const canCreatePet = () => {
        return pet.ime != "" &&
                (pet.starost != "" && pet.starost >= 0 && pet.starost <= 9999 && pet.starost % 1 == 0) &&
                pet.pol != "" &&
                (pet.tezina != "" && pet.tezina >= 0 && pet.tezina <= 9999 && pet.tezina % 1 == 0) &&
                pet.opis != "" &&
                pet.kategorijaId != -1
    }

    const addValueInputChange = (e) => {
        let newPet = { ...pet }

        const name = e.target.name;
        const value = e.target.value;

        newPet[name] = value
        setPet(newPet);
    }

    return (
        <div>
            <h1>Dodavanje ljubimca</h1>
            <Form>
                <Form.Group>
                    <Form.Label>Ime</Form.Label>
                    <Form.Control
                        onChange={(e) => addValueInputChange(e)}
                        name="ime"
                        value={pet.ime}
                        as="input"
                    ></Form.Control>
                </Form.Group>
                <Form.Group>
                    <Form.Label>Starost</Form.Label>
                    <Form.Control
                        onChange={(e) => addValueInputChange(e)}
                        name="starost"
                        value={pet.starost}
                        as="input"
                        type="number"
                        min="0"
                        step="1"
                    ></Form.Control>
                </Form.Group>
                <Form.Group>
                    <Form.Label>Pol</Form.Label>
                    <Form.Control
                        onChange={(e) => addValueInputChange(e)}
                        name="pol"
                        value={pet.pol}
                        as="input"
                    ></Form.Control>
                </Form.Group>
                <Form.Group>
                    <Form.Label>Tezina(kg)</Form.Label>
                    <Form.Control
                        onChange={(e) => addValueInputChange(e)}
                        name="tezina"
                        value={pet.tezina}
                        as="input"
                        type="number"
                        min="0"
                        step="0.1"
                    ></Form.Control>
                </Form.Group>
                <Form.Group>
                    <Form.Label>Opis</Form.Label>
                    <Form.Control
                        onChange={(e) => addValueInputChange(e)}
                        name="opis"
                        value={pet.opis}
                        as="input"
                    ></Form.Control>
                </Form.Group>
                <Form.Group>
                    <Form.Label>Kategorija</Form.Label>
                    <Form.Control
                        onChange={(e) => addValueInputChange(e)}
                        name="kategorijaId"
                        value={pet.kategorijaId}
                        as="select"
                    >
                        <option value={-1}>Odaberi kategoriju</option>
                        {category.map((cat) => {
                            return (
                                <option value={cat.id} key={cat.id}>
                                    {cat.naziv}
                                </option>
                            );
                        })}
                    </Form.Control>
                </Form.Group>
                <Button disabled={!canCreatePet()} variant="primary" onClick={() => doAdd()}>
                    Kreiraj ljubimca
                </Button>
            </Form>
        </div>
    )

}

export default Add;