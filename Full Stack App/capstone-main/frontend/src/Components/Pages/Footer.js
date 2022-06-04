import Container from '@mui/material/Container'
import Grid from '@mui/material/Grid'
import Box from '@mui/material/Box'
import Link from '@mui/material/Link'


export default function Footer(){
    return(
        <footer>
        <Box  
        bgcolor="text.secondary" color="white">
            <Container maxWidth='lg'>
            <Grid container rowSpacing={1} columnSpacing={{ xs: 1, sm: 2, md: 3 }}>
                    <Grid item xs={12} sm={4}>
                        <Box borderBottom={3} >
                            <h6>
                                Account
                            </h6>
                        </Box>
                        <Box>
                            <Link href='/login' color="inherit">Login</Link>
                        </Box>
                        <Box>
                            <Link href='/register' color="inherit">Register</Link>
                        </Box>
                    </Grid>
                    <Grid item xs={12} sm={4}>
                        <Box borderBottom={3} >
                            <h6>
                                Support
                            </h6>
                        </Box>
                        <Box>
                            <Link href='/contact' color="inherit">Contact</Link>
                        </Box>
                        <Box>
                            <Link href='/terms-of-service' color="inherit">Terms of Service</Link>
                        </Box>
                        <Box>
                            <Link href='/privacy' color="inherit">Privacy Policy</Link>
                        </Box>
                    </Grid>
                    <Grid item xs={12} sm={4}>
                        <Box borderBottom={3} >
                            <h6>
                                Information
                            </h6>
                        </Box>
                        <Box>
                            <Link href='/contact' color="inherit">About Us</Link>
                        </Box>
                        <Box>
                            <Link href='/support' color="inherit">News And Updates</Link>
                        </Box>
                    </Grid>
                </Grid>
                <Box textAlign="center" pt={{xs:5, sm:10}} pb={{xs:5, sm:0}}>
                    BREWERY FINDER &reg; {new Date().getFullYear()}
                </Box>
            </Container>
        </Box>
    </footer>
    )
}