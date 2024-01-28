import React, {useEffect, useRef, useState} from "react";
import axios from "axios";
import '../styling.css';
import { NewToDo, ToDo } from '../types';

export default function NewToDoForm() {
    const [showPopUpForm, setShowPopUpForm] = useState(false);
    const [descriptionNewToDo, setDescriptionNewToDo] = useState('');
    const inputValid = useRef<HTMLInputElement | null>(null);
    const [savedToDo, setSavedToDo] = useState<ToDo>({id: '', description: '', status: '', date: ''});
    const [showPopUpSavedToDo, setShowPopUpSavedToDo] = useState(false);
    const [currentDate, setCurrentDate] = useState<string>('');

    useEffect(() => {
        const today = new Date();
        const day = today.getDate();
        const month = today.getMonth() + 1;
        const formatNumber = (number: number) => (number < 10 ? `0${number}` : number);
        const formattedDate = `${formatNumber(day)}-${formatNumber(month)}-${today.getFullYear()}`;
        setCurrentDate(formattedDate);
    }, []);

    const saveNewToDo = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        setSavedToDo({id: '', description: '', status: '', date: ''})
        if (descriptionNewToDo === '') {
            if (inputValid.current) {
                inputValid.current.classList.add('error');
            }
            return;
        }
        axios.post('/api/todos', {
            description: descriptionNewToDo,
            status: "OPEN",
            date: currentDate
        } as NewToDo)
            .then(response => {
                setSavedToDo(response.data as ToDo)
                setShowPopUpForm(false)
                setShowPopUpSavedToDo(true)
                if (response.data.id !== '') {
                    setTimeout(() => {
                        setShowPopUpSavedToDo(false);
                    }, 2500);
                }
            })
            .catch(error => {
                console.error('Error fetching data:', error);
            });
    };

    return (
        <>
            <button id="buttonNewToDo" onClick={() => {
                setShowPopUpSavedToDo(false)
                setShowPopUpForm(true)
                setDescriptionNewToDo("")
            }}>New
            </button>
            {showPopUpForm && (
                <div className="popUp">
                    <button id="buttonCloseFormNewToDo" onClick={() => {
                        setShowPopUpForm(false);
                    }}>X
                    </button>
                    <form onSubmit={saveNewToDo}>
                        <label>
                            <h2 id="popUpH2">New To Do:</h2>
                            <input
                                ref={inputValid}
                                type="text"
                                value={descriptionNewToDo}
                                onChange={event => {
                                    if (inputValid.current) {
                                        inputValid.current.classList.remove('error');
                                    }
                                    setDescriptionNewToDo(event.target.value)
                                }}
                            />
                        </label>
                        <button id="buttonSaveFormNewToDo">Save</button>
                    </form>
                </div>
            )}
            {showPopUpSavedToDo && (
                <div className="popUp">
                    {savedToDo.id === '' ? <p>Saving ...</p> : (
                        <div>
                            <button id="buttonCloseFormNewToDo" onClick={() => setShowPopUpSavedToDo(false)}>
                                X
                            </button>
                            <p>You added {savedToDo.description}.</p>
                            <p className="date">{savedToDo.date}</p>
                        </div>
                    )}
                </div>
            )}
        </>
    );
}