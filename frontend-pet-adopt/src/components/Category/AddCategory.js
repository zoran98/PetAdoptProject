import { useState } from "react"
import { Button, Form, FormControl, FormGroup, FormLabel } from "react-bootstrap"
import { useNavigate } from "react-router-dom"
import PetAdoptAxios from "../../apis/PetAdoptAxios";



const Add = () => {

    const emptyCategory = {
        naziv: ""
    }

    const [category, setCategory] = useState(emptyCategory)
    const navigate = useNavigate()

    const doAdd = () => {
        PetAdoptAxios.post("/kategorije", category)
        .then((res) => {
            console.log(res)
            setCategory(category)
            navigate("/category")
        })
        .catch((error) => {
            console.log(error)
            alert("Doslo je do greske prilikom dodavanja kategorije!")
        })
    }

    const canCreateCategory = () => {
        return category.naziv != ""
    }

    const addValueInputChange = (e) => {
        let newCategory = { ...category }

        const name = e.target.name;
        const value = e.target.value;

        newCategory[name] = value
        setCategory(newCategory);
    }

    return (
        <div>
            <h1>Dodavanje kategorije</h1>
                <Form>
                    <FormGroup>
                        <FormLabel>Naziv</FormLabel>
                        <FormControl
                            onChange={(e) => addValueInputChange(e)}
                            name="naziv"
                            value={category.naziv}
                            as="input">
                        </FormControl>
                    </FormGroup>
                    <Button
                    disabled={!canCreateCategory()} variant="primary" onClick={() => doAdd()}>
                        Kreiraj kategoriju
                    </Button>
                </Form>
        </div>
    )
}

export default Add;