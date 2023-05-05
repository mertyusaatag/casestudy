import ButtonGroup from "./ButtonGroup";

const TableComponent = (data) => {

    return (
        <tr key={data.imdbId}>
            <td>{data.title}</td>
            <td>{data.actors}</td>
            <td>{data.type}</td>
            <td>{data.imdbRating}</td>
            <td><ButtonGroup {...data}/></td>
        </tr>

    );
};

export default TableComponent;