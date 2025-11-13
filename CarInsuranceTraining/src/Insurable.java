
//  Interface defining the contract for calculating insurance.
//  To see what type of insurance plan we are going to use
 
public interface Insurable {
    Insurance calculateInsurance(boolean includeThirdParty);
}