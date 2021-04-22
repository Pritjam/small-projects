writeback_input->valm = 0;
writeback_input->status = STAT_AOK;

switch (memory_output->icode) {
    case I_HALT:
        writeback_input->status = STAT_HLT;
        break;

    case I_NOP:
        break;

    case I_RRMOVQ:
        break;  // aka CMOVQ

    case I_IRMOVQ:
        break;

    case I_RMMOVQ:
        mem_write = true;
        mem_addr = memory_output->vale;
        mem_data = memory_output->vala;
        break;

    case I_MRMOVQ:
        dmem_error |= !get_word_val(mem, memory_output->vale, &writeback_input->valm);
        break;

    case I_ALU:
        break;

    case I_JMP:
        break;

    case I_CALL:
        mem_write = true;
        mem_addr = memory_output->vale;
        mem_data = memory_output->vala;
        break;

    case I_RET:
        dmem_error |= !get_word_val(mem, memory_output->vala, &writeback_input->valm);
        mem_read = true;
        mem_addr = memory_output->vala;
        
        break;

    case I_PUSHQ:
        mem_write = true;
        mem_addr = memory_output->vale;
        mem_data = memory_output->vala;
        break;

    case I_POPQ:
        dmem_error |= !get_word_val(mem, memory_output->vala, &writeback_input->valm);
        break;

    default:
        printf("icode is not valid (%d)", memory_output->icode);
        break;
}