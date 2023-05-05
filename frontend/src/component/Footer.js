export default function Footer() {
    return (
        <footer bgColor='light' className='text-center text-lg-left'>
            <div className='text-center p-3' style={{ backgroundColor: 'rgba(0, 0, 0, 0.2)' } }>
                &copy; {new Date().getFullYear()} Copyright:{' '}
                <a className='text-dark'>
                    mertyusaatag
                </a>
            </div>
        </footer>
    );
}